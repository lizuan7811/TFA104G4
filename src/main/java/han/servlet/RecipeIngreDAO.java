package han.servlet;

import java.util.List;

public interface RecipeIngreDAO {
	public void insert(RecipeIngreVO recipeIngreVO);
	public void update(RecipeIngreVO recipeIngreVO);
	public void delete(int idRecipeIngre);
	public RecipeIngreVO findByPK(int idRecipeIngre);
	public List<RecipeIngreVO> getAll();
}
