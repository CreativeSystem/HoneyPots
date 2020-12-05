import { Request, Response } from 'express'
import { injectable } from 'inversify'

import { Recipe } from '../entities'
import { RecipeActionType } from '../entities/RecipeAction'
import { GetController } from './@types'

@injectable()
export class MyLikedRecipe extends GetController {
  route = '/recipe/liked'
  authentication = true

  async handle(req: Request, res: Response) {
    const recipes = await Recipe.getRepository()
      .createQueryBuilder()
      .where(
        `id in 
          (
            select recipe_id 
            from recipe_action 
            where user_id = :userId 
            and action = :action 
          )`,
        {
          userId: req.user,
          action: RecipeActionType.LIKED
        }
      )
      .orderBy('created_at', 'DESC')
      .getMany()

    return res.json(recipes)
  }
}
