package han.Recipe;

import java.util.List;

public interface RecipeDAO {
	public void insert(RecipeVO recipeVO);
	public void update(RecipeVO recipeVO);
	public void delete(Integer idRecipe);
	public RecipeVO findByPK(Integer idRecipe);
	public List<RecipeVO> getAll();
}
