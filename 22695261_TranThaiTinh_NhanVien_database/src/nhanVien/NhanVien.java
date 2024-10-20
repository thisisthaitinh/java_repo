package nhanVien;

public class NhanVien {
	private String nv_id;
    private String nv_ho;
	private String nv_ten;
    private int nv_tuoi;
    private String nv_gt;
    private double nv_luong;
    
    public NhanVien(String nv_id, String nv_ho, String nv_ten, int nv_tuoi, String nv_gt, double nv_luong) {
    	super();
    	this.nv_id = nv_id;
    	this.nv_ho = nv_ho;
    	this.nv_ten = nv_ten;
    	this.nv_tuoi = nv_tuoi;
    	this.nv_gt = nv_gt;
    	this.nv_luong = nv_luong;
    }
    
    public String getNv_id() {
		return nv_id;
	}
	public String getNv_ho() {
		return nv_ho;
	}
	public String getNv_ten() {
		return nv_ten;
	}
	public int getNv_tuoi() {
		return nv_tuoi;
	}
	public String getNv_gt() {
		return nv_gt;
	}
	public double getNv_luong() {
		return nv_luong;
	}
	public void setNv_id(String nv_id) {
		this.nv_id = nv_id;
	}
	public void setNv_ho(String nv_ho) {
		this.nv_ho = nv_ho;
	}
	public void setNv_ten(String nv_ten) {
		this.nv_ten = nv_ten;
	}
	public void setNv_tuoi(int nv_tuoi) {
		this.nv_tuoi = nv_tuoi;
	}
	public void setNv_gt(String nv_gt) {
		this.nv_gt = nv_gt;
	}
	public void setNv_luong(double nv_luong) {
		this.nv_luong = nv_luong;
	} 
}
