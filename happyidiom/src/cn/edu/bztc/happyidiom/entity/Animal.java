package cn.edu.bztc.happyidiom.entity;

public class Animal {
	private int id;
	private String name;//成语名称
	private String pronounce;//成语发音
	private String explain;//成语解释
	private String antonym;//反义词
	private String homoionym;//同义词
	private String derivation;//源自
	private String examples;//例子
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPronounce() {
		return pronounce;
	}
	public void setPronounce(String pronounce) {
		this.pronounce = pronounce;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getAntonym() {
		return antonym;
	}
	public void setAntonym(String antonym) {
		this.antonym = antonym;
	}
	public String getHomoionym() {
		return homoionym;
	}
	public void setHomoionym(String homoionym) {
		this.homoionym = homoionym;
	}
	public String getDerivation() {
		return derivation;
	}
	public void setDerivation(String derivation) {
		this.derivation = derivation;
	}
	public String getExamples() {
		return examples;
	}
	public void setExamples(String examples) {
		this.examples = examples;
	}
	
}
