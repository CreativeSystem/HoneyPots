import createValidatorRules from '~/helpers/createValidatorRules'

import { LoginRequest } from '../controllers/@types'

export default createValidatorRules<LoginRequest>({
  accessToken: ['required', 'string'],
  social: ['required', 'string'],
  tokenId: ['required_if:social,google', 'string']
})
