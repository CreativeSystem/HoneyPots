import inversifyContainer from '~/config/inversify'
import bindDependencies from '~/helpers/bindDependencies'
import { Request, Response, NextFunction } from 'express'
import Validator, { Rules } from 'validatorjs'

import { UserToken } from '../@types'
import { TokenProvider } from '../providers'

export default async (
  request: Request,
  response: Response,
  nextFunction: NextFunction
) => {
  const tokenProvider = inversifyContainer.get<TokenProvider>('TokenProvider')
  const rules = inversifyContainer.get<Rules>('AuthenticationValidator')

  const validator = new Validator(request.headers, rules)
  if (!validator.check()) {
    response.status(400).json(validator.errors.errors)
  }
  const authorization = request.headers.authorization as string
  const [, token] = authorization.split(' ')
  try {
    const user = tokenProvider.verify<UserToken>(token)
    request.user = user.id
    nextFunction()
  } catch (e) {
    response.status(401).json({
      error: 'invalid_token',
      error_description: 'token invalid'
    })
  }
}
