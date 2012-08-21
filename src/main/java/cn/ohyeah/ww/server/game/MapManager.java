package cn.ohyeah.ww.server.game;

import cn.ohyeah.stb.util.ByteBuffer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MapManager {
    private static final int MAP_VERSION = 2;
    private static final String MAP_MAGIC = "MWWM";
    private static final String TAG_INFLUENCE = "TINF";
    private static final String TAG_REGION = "TREG";
    private static final String TAG_TILE = "TILE";
    private static final String TAG_FLAG = "FLAG";

    private String rootPath;
    private String fileExt;
    private ConcurrentHashMap<Integer, MapNode[]> maps;
    private ConcurrentHashMap<Integer, MapInfo> mapInfos;

    @Autowired
    @Qualifier("mapFileRootPath")
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Autowired
    @Qualifier("mapFileExt")
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    @PostConstruct
    synchronized public void initMap() {
        File mapDir = FileUtils.getFile(rootPath);
        if (mapDir!=null && mapDir.isDirectory()) {
            File[] subdirs = mapDir.listFiles();
            for (File file : subdirs) {
                if (isMapDir(file)) {
                    try {
                        int players = Integer.parseInt(file.getName());
                        loadMaps(file, players);
                    } catch (IOException e) {
                        throw new MapLoadException("加载地图失败", e);
                    }
                }
            }
        }
    }

    private boolean isMapDir(File path) {
        return path.isDirectory()&&StringUtils.isNumeric(path.getName());
    }

    private void loadMaps(File path, int players) throws IOException {
        Collection<File> mapFiles = FileUtils.listFiles(path, new String[]{fileExt}, false);
        MapNode[] mapNodes = new MapNode[mapFiles.size()];
        String[] names = new String[mapFiles.size()];
        int count = 0;
        Date now = new Date();
        for (File file : mapFiles) {
            names[count] = file.getName();
            mapNodes[count] = new MapNode(FileUtils.readFileToByteArray(file));
            mapNodes[count].loadTime = now;
            mapNodes[count].name = file.getName();
            mapNodes[count].path = file.getAbsolutePath();
            mapNodes[count].players = players;
            count++;
        }
        maps.put(players, mapNodes);
        MapInfo info = new MapInfo();
        info.loadTime = now;
        info.names = names;
        mapInfos.put(players, info);
    }

    public MapNode randomLoadMap(int players) {
        MapNode[] mapNodes = maps.get(players);
        int rndInt = new Random().nextInt(mapNodes.length);
        return mapNodes[rndInt];
    }


    private class MapInfo {
        Date loadTime;
        String[] names;
    }

    public class MapNode {
        byte[] data;
        GameMap map;
        int version;
        int players;
        Date loadTime;
        String name;
        String path;

        MapNode(byte[] data) {
            this.data = data;
            this.map = readGameMap(data);
        }

        public byte[] getData() {
            return data;
        }
        public GameMap getMap() {
            return map;
        }
        public int getPlayers() {
            return players;
        }

        private void readMapHead(ByteBuffer buf) {
            String magic = new String(buf.readBytes(4));
            if (!MAP_MAGIC.equals(magic))
            {
                throw new MapLoadException("地图格式错误");
            }
            int version = buf.readInt();
            if (version > MAP_VERSION)
            {
                throw new MapLoadException("地图版本不支持");
            }
            buf.skipReader(11*4);
        }

        private Influence[] readInfluences(ByteBuffer buf) {
            String tag = new String(buf.readBytes(4));
            if (!TAG_INFLUENCE.equals(tag))
            {
                throw new MapLoadException("势力标识错误");
            }
            int count = buf.readInt();
            Influence[] influences = new Influence[count];
            for (int i = 0; i < count; ++i) {
                Influence inf = new Influence();
                inf.setId((short)i);
                inf.setName(buf.readString());
                buf.skipReader(4);
                influences[i] = inf;
            }
            return influences;
        }

        private Region[] readRegions(ByteBuffer buf) {
            String tag = new String(buf.readBytes(4));
            if (!TAG_REGION.equals(tag))
            {
                throw new MapLoadException("区块标识错误");
            }
            int count = buf.readInt();
            buf.skipReader(4);
            Region[] regions = new Region[count];
            for (int i = 0; i < count; ++i) {
                Region region = new Region();
                region.setId((short)i);
                region.setName(buf.readString());
                region.setSoldiers(buf.readShort());
                region.setInfluenceId(buf.readShort());
                region.setUpRegionId(buf.readShort());
                region.setLeftRegionId(buf.readShort());
                region.setDownRegionId(buf.readShort());
                region.setRightRegionId(buf.readShort());
                buf.skipReader(4);
                if (version >= 2) {
                    buf.skipReader(4);
                    short[] adjacentRegions = new short[buf.readShort()];
                    for (int j = 0; j < adjacentRegions.length; ++j) {
                        adjacentRegions[j] = buf.readShort();
                    }
                    region.setAdjacentRegions(adjacentRegions);
                }
                regions[i] = region;
            }
            return regions;
        }

        private void readTiles(ByteBuffer buf) {
            String tag = new String(buf.readBytes(4));
            if (!TAG_TILE.equals(tag))
            {
                throw new MapLoadException("贴片标识错误");
            }
            int rows = buf.readInt();
            int cols = buf.readInt();
            buf.skipReader(rows*cols*2);
        }

        private void readTilesFlag(ByteBuffer buf)
        {
            String tag = new String(buf.readBytes(4));
            if (!TAG_FLAG.equals(tag))
            {
                throw new MapLoadException("贴片标志标识错误");
            }
            int rows = buf.readInt();
            int cols = buf.readInt();
            buf.skipReader(rows*cols*2);
        }

        public GameMap readGameMap(byte[] data) {
            GameMap map = new GameMap();
            ByteBuffer buf = new ByteBuffer(data);
            readMapHead(buf);
            map.setInfluences(readInfluences(buf));
            map.setRegions(readRegions(buf));
            readTiles(buf);
            readTilesFlag(buf);
            return map;
        }
    }

}
