import { Request, Response, NextFunction } from 'express'

export default function () {
  return async (
    request: Request,
    _response: Response,
    nextFunction: NextFunction
  ) => {
    request.all = { ...request.query, ...request.body }
    nextFunction()
  }
}
