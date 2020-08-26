import 'reflect-metadata'
import '@config/env'

import { createConnection } from 'typeorm'

import inversifyContainer from '@config/inversify'
import TypeormConfig from '@config/typeorm'

import AppController from './app'

const appController = inversifyContainer.get(AppController)

createConnection(TypeormConfig)
  .then(async () => {
    appController.listen(parseInt(process.env.PORT || '3333'))
  })
  .catch(console.error)
