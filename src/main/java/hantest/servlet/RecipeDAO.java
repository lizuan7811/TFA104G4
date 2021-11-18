package hantest.servlet;

import java.util.List;

public interface RecipeDAO {
	void insert(RecipeVO recipeVO);
	void update(RecipeVO recipeVO);
	void delete(int idRecipe);
	RecipeVO findByPK(int idRecipe);
	List<RecipeVO> getAll();
}
