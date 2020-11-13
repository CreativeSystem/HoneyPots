import 'reflect-metadata'

import { Container } from 'inversify'
import { Rules } from 'validatorjs'

import * as Controllers from '@app/controllers'
import { Controller } from '@app/controllers/@types/Controller'
import AppController from '@app/index'
import * as Providers from '@app/providers'
import * as UseCases from '@app/useCases'
import * as Validators from '@app/validators'

const inversifyContainer = new Container()

Object.values(Controllers).forEach(controller =>
  inversifyContainer.bind<Controller>('Controller').to(controller)
)

Object.entries(process.env).forEach(entry => {
  const [name, env] = entry
  if (env) {
    inversifyContainer.bind<string>(name).toConstantValue(env)
  }
})

Object.entries(Providers).forEach(entry => {
  const [name, provider] = entry
  inversifyContainer.bind(name).to(provider)
})

Object.entries(UseCases).forEach(entry => {
  const [name, useCase] = entry
  inversifyContainer.bind(name).to(useCase)
})

Object.entries(Validators).forEach(entry => {
  const [name, validator] = entry
  inversifyContainer.bind<Rules>(name).toConstantValue(validator)
})

inversifyContainer.bind(AppController).to(AppController)

export default inversifyContainer
