package han.RecipeIngre;

import java.util.List;

public interface RecipeIngreDAO {
	public void insert(RecipeIngreVO recipeIngreVO);
	public void update(RecipeIngreVO recipeIngreVO);
	public void delete(Integer idRecipeIngre);
	public RecipeIngreVO findByPK(Integer idRecipeIngre);
	public List<RecipeIngreVO> getAll();
	public List<RecipeIngreVO> findList(Integer idRecipe);
	public void deleteList(Integer idRecipe);
}
