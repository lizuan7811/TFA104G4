package hantest.servlet;

import java.io.Serializable;

public class RecipeVO implements Serializable{
	private int idRecipe; //食譜編號
	private String text; //內容
	private String descrip; //步驟說明
	private byte[] photo; //圖片
	
	public RecipeVO() {
		
	}
	
	public RecipeVO(int idRecipe, String text, String descrip, byte[] photo) {
		this.idRecipe = idRecipe;
		this.text = text;
		this.descrip = descrip;
		this.photo = photo;
	}

	public int getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	
}
