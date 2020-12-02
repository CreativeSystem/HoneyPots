import { injectable } from 'inversify'
import { v4 as uuid } from 'uuid'

import { Recipe } from '../entities'
import { CreateRecipeUseCase, CreateRecipeUseCaseParameter } from './@types'

@injectable()
export default class implements CreateRecipeUseCase {
  async execute({ recipe: { content, userId } }: CreateRecipeUseCaseParameter) {
    const recipe = await Recipe.create({
      id: uuid(),
      content: content,
      user: {
        id: userId
      }
    }).save()

    return {
      id: recipe.id,
      content: recipe.content
    }
  }
}
