package rutas;

public class Ruta {
	
	private String origenDestino;
	private String empresa;
	private double precioB;
	private double precioPr;
	private double precioPl;
	
	public Ruta(String origenDestino, String empresa, double precioB, double precioPr, double precioPl) {
		super();
		this.origenDestino = origenDestino;
		this.empresa = empresa;
		this.precioB = precioB;
		this.precioPr = precioPr;
		this.precioPl = precioPl;
	}

	public String getOrigenDestino() {
		return origenDestino;
	}

	public void setOrigenDestino(String origenDestino) {
		this.origenDestino = origenDestino;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public double getPrecioB() {
		return precioB;
	}

	public void setPrecioB(double precioB) {
		this.precioB = precioB;
	}

	public double getPrecioPr() {
		return precioPr;
	}

	public void setPrecioPr(double precioPr) {
		this.precioPr = precioPr;
	}

	public double getPrecioPl() {
		return precioPl;
	}

	public void setPrecioPl(double precioPl) {
		this.precioPl = precioPl;
	}

	@Override
	public String toString() {
		return "Ruta [origenDestino=" + origenDestino + ", empresa=" + empresa + ", precioB=" + precioB + ", precioPr="
				+ precioPr + ", precioPl=" + precioPl + "]";
	}
	
}
