package han.servlet;

import java.util.List;

public interface RecipeDAO {
	public void insert(RecipeVO recipeVO);
	public void update(RecipeVO recipeVO);
	public void delete(int idRecipe);
	public RecipeVO findByPK(int idRecipe);
	public List<RecipeVO> getAll();
}
