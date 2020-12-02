import createValidatorRules from '~/helpers/createValidatorRules'

import { CreateRecipeDTO } from '../useCases/@types'

export default createValidatorRules<CreateRecipeDTO>({
  content: {
    images: ['required', 'array'],
    name: ['required', 'string'],
    types: ['array'],
    durationInMinutes: ['required', 'numeric'],
    description: ['required', 'string'],
    steps: ['required', 'array']
  }
})
