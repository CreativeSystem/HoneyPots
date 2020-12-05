import { UseCase } from '.'

export interface ToggleLikeUseCaseParameter {
  recipe: string
  user: string
}

export type ToggleLikeUseCase = UseCase<ToggleLikeUseCaseParameter, void>
