export interface RecipeContent {
  name: string
  types: string[] | null
  description: string
  images: string[]
  durationInMinutes: number
  steps: Step[]
}

export interface Ingredient {
  name: string
  quantity: number
  unit: string
}

export interface Step {
  title: string
  content: Ingredient[] | string[] | Step[]
}
