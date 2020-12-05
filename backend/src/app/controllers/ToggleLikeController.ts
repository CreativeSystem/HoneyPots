import { Request, Response } from 'express'
import { injectable } from 'inversify'

import { RecipeAction } from '../entities'
import { RecipeActionType } from '../entities/RecipeAction'
import { PostController } from './@types'

@injectable()
export class ToggleLikeController extends PostController {
  route = '/recipe/:recipeId/toggle-like'
  authentication = true

  async handle(req: Request, res: Response) {
    const { recipeId } = req.params

    const like = {
      action: RecipeActionType.LIKED,
      recipe: {
        id: recipeId
      },
      user: {
        id: req.user
      }
    }

    let recipeAction = await RecipeAction.findOne({
      relations: ['user', 'recipe'],
      where: like
    })

    if (recipeAction) {
      await recipeAction.remove()
    } else {
      recipeAction = RecipeAction.create(like)
      await recipeAction.save()
    }

    return res.status(204).send()
  }
}
