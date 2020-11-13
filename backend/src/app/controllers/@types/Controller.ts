import { Express, Request, RequestHandler, Response } from 'express'
import { PathParams } from 'express-serve-static-core'
import { injectable } from 'inversify'

export enum HttpMethods {
  GET = 'GET',
  PUT = 'PUT',
  POST = 'POST',
  DELETE = 'DELETE'
}

@injectable()
export abstract class Controller {
  readonly method: HttpMethods
  readonly route: PathParams
  // eslint-disable-next-line @typescript-eslint/no-empty-function
  middlewares(): RequestHandler[] | void {}
  abstract handle(req: Request, res: Response): any

  handles() {
    const middlewares = this.middlewares() || []
    return [...middlewares, this.handle.bind(this)]
  }

  applyRoute(express: Express) {
    switch (this.method) {
      case HttpMethods.GET:
        express.get(this.route, ...this.handles())
        break
      case HttpMethods.POST:
        express.post(this.route, ...this.handles())
        break
      case HttpMethods.PUT:
        express.put(this.route, ...this.handles())
        break
      case HttpMethods.DELETE:
        express.delete(this.route, ...this.handles())
        break
    }
  }
}

export abstract class PostController extends Controller {
  method = HttpMethods.POST
}

export abstract class GetController extends Controller {
  method = HttpMethods.GET
}

export abstract class PutController extends Controller {
  method = HttpMethods.PUT
}

export abstract class DeleteController extends Controller {
  method = HttpMethods.DELETE
}
