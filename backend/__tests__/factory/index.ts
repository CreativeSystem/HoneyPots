import factory from 'factory-girl'
import faker from 'faker'

import { User } from '../../src/app/entities'
import TypeOrmAdapter from './TypeOrmAdapter'

factory.setAdapter(new TypeOrmAdapter())

factory.define<Partial<User>>('user', User, {
  name: faker.name.findName(),
  email: faker.internet.email(),
  password: faker.random.alphaNumeric(12)
})

export default factory
