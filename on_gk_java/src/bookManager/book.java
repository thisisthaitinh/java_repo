package bookManager;

public class book {
	// declaration
	private String maSach, tuaSach, ISB_num, tacGia, nhaXB;
	private int namXB, soTrang;
	private double donGia;

	// constructor
	public book(String maSach, String tuaSach, int namXB, int soTrang, String iSB_num, String tacGia, String nhaXB,  
			double donGia) {
		super();
		this.maSach = maSach;
		this.tuaSach = tuaSach;
		ISB_num = iSB_num;
		this.tacGia = tacGia;
		this.nhaXB = nhaXB;
		this.namXB = namXB;
		this.soTrang = soTrang;
		this.donGia = donGia;
	}
	
	// getter and setter methods
	public String getMaSach() {
		return maSach;
	}
	
	public String getTuaSach() {
		return tuaSach;
	}
	
	public String getISB_num() {
		return ISB_num;
	}
	
	public String getTacGia() {
		return tacGia;
	}
	
	public String getNhaXB() {
		return nhaXB;
	}
	
	public int getNamXB() {
		return namXB;
	}
	
	public int getSoTrang() {
		return soTrang;
	}
	
	public double getDonGia() {
		return donGia;
	}
	
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	
	public void setTuaSach(String tuaSach) {
		this.tuaSach = tuaSach;
	}
	
	public void setISB_num(String iSB_num) {
		ISB_num = iSB_num;
	}
	
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	
	public void setNhaXB(String nhaXB) {
		this.nhaXB = nhaXB;
	}
	
	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}
	
	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}
	
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	
	// toString method
	@Override
	public String toString() {
		return "book [maSach=" + maSach + ", tuaSach=" + tuaSach + ", ISB_num=" + ISB_num + ", tacGia=" + tacGia
				+ ", nhaXB=" + nhaXB + ", namXB=" + namXB + ", soTrang=" + soTrang + ", donGia=" + donGia + "]";
	}
}
