import { Request, Response } from 'express'
import { injectable } from 'inversify'

import { RecipeAction } from '../entities'
import { RecipeActionType } from '../entities/RecipeAction'
import { PostController } from './@types'

@injectable()
export class VisualizeRecipeController extends PostController {
  route = '/recipe/:recipeId/visualize'
  authentication = true

  async handle(req: Request, res: Response) {
    const { recipeId } = req.params

    const visualize = {
      action: RecipeActionType.VISUALIZED,
      recipe: {
        id: recipeId
      },
      user: {
        id: req.user
      }
    }

    let recipeAction = await RecipeAction.findOne({
      relations: ['user', 'recipe'],
      where: visualize
    })

    if (!recipeAction) {
      recipeAction = RecipeAction.create(visualize)
      await recipeAction.save()
    }

    return res.status(204).send()
  }
}
