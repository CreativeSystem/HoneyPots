/* eslint-disable @typescript-eslint/no-unused-vars */
import { getRepository } from 'typeorm'

export default class TypeOrmAdapter {
  build(Model, props) {
    return this.set(props, new Model(), Model)
  }

  async save(model, Model) {
    const repository = getRepository(Model)
    return await repository.save(model)
  }

  async destroy(model, Model) {
    const repository = getRepository(Model)
    return repository.delete(model)
  }

  get(model, attr, _Model) {
    return model[attr]
  }

  set(props, model, _Model) {
    Object.keys(props).forEach(key => {
      model[key] = props[key]
    })
    return model
  }
}
