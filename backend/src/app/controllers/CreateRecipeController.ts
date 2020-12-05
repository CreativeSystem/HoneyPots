import convertBase64ToImage from '~/helpers/convertBase64ToImage'
import { Request, Response } from 'express'
import { inject, injectable } from 'inversify'
import { v4 as uuid } from 'uuid'
import { Rules } from 'validatorjs'

import { Image } from '../entities'
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

    if (recipe.content) {
      const savedImages: string[] = []
      const images = recipe.content.images
      for (let i = 0; i < images.length; i++) {
        const id = uuid()
        const name = `${id}.png`
        convertBase64ToImage(`${process.env.IMAGE_DIR}/${name}`, images[i])
        const imageEntity = Image.create({
          id,
          name,
          originalName: name
        })

        await imageEntity.save()
        savedImages.push(id)
      }
      recipe.content.images = savedImages
    }
    const createdRecipe = await this.createRecipeUseCase.execute({ recipe })

    return res.json(createdRecipe)
  }

  middlewares() {
    return [ValidationMiddleware(this.createRecipeValidator)]
  }
}
