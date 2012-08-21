package cn.ohyeah.ww.server.game;

public class GameMap {
    private Region[] regions;
    private Influence[] influences;

    protected GameMap() {}

    public Region[] getRegions() {
    	return regions;
    }

    public Influence[] getInfluences() {
    	return influences;
    }

    public void setRegions(Region[] regions) {
        this.regions = regions;
    }

    public void setInfluences(Influence[] influences) {
        this.influences = influences;
    }

    protected Influence getInfluence(int id) {
        if (id < 0 || id >= influences.length) {
            return null;
        }
    	return influences[id];
    }

    protected Influence getInfluence(Region region) {
    	return influences[region.getInfluenceId()];
    }

    protected Region getRegion(int id) {
        if (id < 0 || id >= regions.length) {
            return null;
        }
    	return regions[id];
    }

    public boolean isConnected(Region region1, Region region2) {
    	if (region1 == null || region2 == null || region1 == region2) {
    		return false;
    	}
    	short[] adjacentRegions = region1.getAdjacentRegions();
    	short region2Id = region2.getId();
    	for (int i = 0; i < adjacentRegions.length; ++i) {
    		if (region2Id == adjacentRegions[i]) {
    			return true;
    		}
    	}
    	return false;
    }

    public boolean isConnected(int regionId1, int regionId2) {
        return isConnected(getRegion(regionId1), getRegion(regionId2));
    }

	public Region[] getAdjacentRegions(Region region) {
		short[] ids = region.getAdjacentRegions();
		Region[] regions = new Region[ids.length];
		for(int i=0;i<ids.length;i++){
			regions[i]=getRegion(ids[i]);
		}
		return regions;
	}

    public Region moveLeft(Region region) {
    	if (region.getLeftRegionId() >= 0)
    	{
    		return regions[region.getLeftRegionId()];
    	}
    	return region;
    }

    public Region moveRight(Region region) {
    	if (region.getRightRegionId() >= 0)
    	{
    		return regions[region.getRightRegionId()];
    	}
    	return region;
    }

    public Region moveUp(Region region) {
    	if (region.getUpRegionId() >= 0)
    	{
    		return regions[region.getUpRegionId()];
    	}
    	return region;
    }

    public Region moveDown(Region region) {
    	if (region.getDownRegionId() >= 0)
    	{
    		return regions[region.getDownRegionId()];
    	}
    	return region;
    }
}
