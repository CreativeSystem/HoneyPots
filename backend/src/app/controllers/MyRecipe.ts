import { Request, Response } from 'express'
import { injectable } from 'inversify'

import { Recipe } from '../entities'
import { GetController } from './@types'

@injectable()
export class MyRecipe extends GetController {
  route = '/me/recipe/'
  authentication = true

  async handle(req: Request, res: Response) {
    const recipes = await Recipe.find({
      relations: ['user'],
      where: {
        user: {
          id: req.user
        }
      }
    })

    return res.json(recipes)
  }
}
