import cors from 'cors'
import express, { Express } from 'express'
import helmet from 'helmet'
import { injectable, multiInject } from 'inversify'
import morgan from 'morgan'

import { Controller } from './controllers/@types'
import MergeQueryAndBodyMiddleware from './middlewares/MergeQueryAndBodyMiddleware'

@injectable()
export default class AppController {
  express: Express
  constructor(@multiInject('Controller') controllers: Controller[]) {
    this.express = express()
    this.middlewares()
    // TODO melhorar Exception Handler
    this.routes(controllers)
    this.express.use((err, req, res, next) => {
      console.error(err.stack)
      res.json({
        erro: err
      })
    })
  }

  middlewares() {
    this.express.use(helmet())
    this.express.use(cors())
    this.express.use(express.json())
    this.express.use(morgan('dev'))
    this.express.use(MergeQueryAndBodyMiddleware())
  }

  routes(controllers: Controller[]) {
    controllers.forEach(controller => controller.applyRoute(this.express))
    this.express.get('/routes', (_req, res) =>
      res.json(
        controllers.map(controller => ({
          method: controller.method.toString(),
          route: controller.route
        }))
      )
    )
  }

  listen(port: number) {
    this.express.listen(port)
  }
}
