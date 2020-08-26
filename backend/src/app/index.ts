import cors from 'cors'
import express, { Express } from 'express'
import helmet from 'helmet'
import { injectable } from 'inversify'
import morgan from 'morgan'

@injectable()
export default class AppController {
  express: Express
  constructor() {
    this.express = express()
    this.middlewares()
    this.routes()
  }

  middlewares() {
    this.express.use(helmet())
    this.express.use(cors())
    this.express.use(express.json())
    this.express.use(morgan('dev'))
  }

  routes() {
    this.express.get('/', (_req, res) => res.json({ hello: 'World' }))
  }

  listen(port: number) {
    this.express.listen(port)
  }
}
