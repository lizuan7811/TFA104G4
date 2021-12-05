package han.RecipeIngre;

import java.io.Serializable;

public class RecipeIngreVO implements Serializable {
	private Integer idRecipeIngre; //食譜食材編號
	private Integer idRecipe; //食譜編號
	private Integer idIngre; //食材編號
	private Integer ingreQuan; //食材數量
	
	public RecipeIngreVO() {
		
	}
	
	public RecipeIngreVO(int idRecipeIngre, int idRecipe, int idIngre, int ingreQuan) {
		this.idRecipeIngre = idRecipeIngre;
		this.idRecipe = idRecipe;
		this.idIngre = idIngre;
		this.ingreQuan = ingreQuan;
	}

	public int getIdRecipeIngre() {
		return idRecipeIngre;
	}

	public void setIdRecipeIngre(int idRecipeIngre) {
		this.idRecipeIngre = idRecipeIngre;
	}

	public int getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public int getIdIngre() {
		return idIngre;
	}

	public void setIdIngre(int idIngre) {
		this.idIngre = idIngre;
	}

	public int getIngreQuan() {
		return ingreQuan;
	}

	public void setIngreQuan(int ingreQuan) {
		this.ingreQuan = ingreQuan;
	}
	
}
