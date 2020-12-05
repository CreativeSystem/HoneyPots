import { RecipeActionType } from '~/app/entities/RecipeAction'

import { UseCase } from '.'

export interface CreateRecipeActionUseCaseParameter {
  recipe: string
  user: string
  action: RecipeActionType
}

export type CreateRecipeActionUseCase = UseCase<
  CreateRecipeActionUseCaseParameter,
  void
>
