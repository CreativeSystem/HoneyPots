import { Request, Response, NextFunction } from 'express'
import Validator from 'validatorjs'

export default function <T>(
  rules: Validator.Rules,
  customMessages?: Validator.ErrorMessages
) {
  return async (
    request: Request,
    response: Response,
    nextFunction: NextFunction
  ) => {
    const data = request.all as T

    const validator = new Validator(data, rules, customMessages)

    validator.checkAsync(nextFunction, () => {
      response.status(400).json(validator.errors.errors)
    })
  }
}
