package hantest.servlet;

import java.util.List;

public interface RecipeIngreDAO {
	void insert(RecipeIngreVO recipeIngreVO);
	void update(RecipeIngreVO recipeIngreVO);
	void delete(int idRecipeIngre);
	RecipeIngreVO findByPK(int idRecipeIngre);
	List<RecipeIngreVO> getAll();
}
