package cn.ohyeah.ww.game;

public class Region {
	private static int borderColor;
	private short id;
	private String name;
	private short soldiers;
	private short influenceId;
	private short leftRegionId;
	private short rightRegionId;
	private short upRegionId;
	private short downRegionId;
	private short centerTileRow;
	private short centerTileCol;
	private short centerTileX;
	private short centerTileY;
	private short[] adjacentRegions;
	private int attackPropId;
	private int defensePropId;

    public int getAttackPropId() {
        return attackPropId;
    }

    public void setAttackPropId(int attackPropId) {
        this.attackPropId = attackPropId;
    }

    public int getDefensePropId() {
        return defensePropId;
    }

    public void setDefensePropId(int defensePropId) {
        this.defensePropId = defensePropId;
    }

    public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public short getSoldiers() {
		return soldiers;
	}
	public void setSoldiers(short soldiers) {
		this.soldiers = soldiers;
	}
	public short getInfluenceId() {
		return influenceId;
	}
	public void setInfluenceId(short influenceId) {
		this.influenceId = influenceId;
	}
	public short getLeftRegionId() {
		return leftRegionId;
	}
	public void setLeftRegionId(short leftRegionId) {
		this.leftRegionId = leftRegionId;
	}
	public short getRightRegionId() {
		return rightRegionId;
	}
	public void setRightRegionId(short rightRegionId) {
		this.rightRegionId = rightRegionId;
	}
	public short getUpRegionId() {
		return upRegionId;
	}
	public void setUpRegionId(short upRegionId) {
		this.upRegionId = upRegionId;
	}
	public short getDownRegionId() {
		return downRegionId;
	}
	public void setDownRegionId(short downRegionId) {
		this.downRegionId = downRegionId;
	}
	public short getCenterTileRow() {
		return centerTileRow;
	}
	public void setCenterTileRow(short centerTileRow) {
		this.centerTileRow = centerTileRow;
	}
	public short getCenterTileCol() {
		return centerTileCol;
	}
	public void setCenterTileCol(short centerTileCol) {
		this.centerTileCol = centerTileCol;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public static void setBorderColor(int borderColor) {
		Region.borderColor = borderColor;
	}
	public static int getBorderColor() {
		return borderColor;
	}
	public void setCenterTileX(short centerTileX) {
		this.centerTileX = centerTileX;
	}
	public short getCenterTileX() {
		return centerTileX;
	}
	public void setCenterTileY(short centerTileY) {
		this.centerTileY = centerTileY;
	}
	public short getCenterTileY() {
		return centerTileY;
	}
	public void setAdjacentRegions(short[] adjacentRegions) {
		this.adjacentRegions = adjacentRegions;
	}
	public short[] getAdjacentRegions() {
		return adjacentRegions;
	}

	public int compareSoldiers(Region region) {
		if (soldiers > region.soldiers) {
			return 1;
		}
		else if (soldiers == region.soldiers) {
			return 0;
		}
		else {
			return -1;
		}
	}
}
