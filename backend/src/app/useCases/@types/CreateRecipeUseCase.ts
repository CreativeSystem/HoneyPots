import { RecipeContent } from '~/app/@types'

import { UseCase } from '.'

export interface CreateRecipeDTO {
  id?: string
  content?: RecipeContent
  userId: string
}

export interface CreateRecipeUseCaseParameter {
  recipe: CreateRecipeDTO
}

export type CreateRecipeUseCase = UseCase<
  CreateRecipeUseCaseParameter,
  Omit<CreateRecipeDTO, 'userId'>
>
