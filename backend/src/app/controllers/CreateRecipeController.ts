import { Request, Response } from 'express'
import { inject, injectable } from 'inversify'
import { Rules } from 'validatorjs'

import ValidationMiddleware from '../middlewares/ValidationMiddleware'
import { CreateRecipeDTO, CreateRecipeUseCase } from '../useCases/@types'
import { PostController } from './@types'

@injectable()
export class CreateRecipeController extends PostController {
  route = '/recipe'
  authentication = true

  @inject('CreateRecipeValidator')
  private createRecipeValidator: Rules

  @inject('CreateRecipeUseCase')
  private createRecipeUseCase: CreateRecipeUseCase

  async handle(req: Request, res: Response) {
    const recipe: CreateRecipeDTO = { ...req.all, userId: req.user }
    const createdRecipe = await this.createRecipeUseCase.execute({ recipe })

    return res.json(createdRecipe)
  }

  middlewares() {
    return [ValidationMiddleware(this.createRecipeValidator)]
  }
}
