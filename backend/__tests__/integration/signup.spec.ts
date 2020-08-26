import 'reflect-metadata'
import request from 'supertest'

import AppController from '../../src/app'
import { User } from '../../src/app/model'
import inversifyContainer from '../../src/config/inversify'
import factory from '../factory'
import connection from '../utils/connection'
const app = inversifyContainer.get(AppController).express

describe('SignUp', () => {
  beforeAll(async () => {
    await connection.create()
  })

  afterAll(async () => {
    await connection.close()
  })

  beforeEach(async () => {
    await connection.clear()
  })

  it('Should create a user with valid information', async () => {
    const buildedUser = await factory.build<User>('user')

    const signUpInfo = {
      email: buildedUser.email,
      name: buildedUser.name,
      password: buildedUser.password,
      password_confirmation: buildedUser.password
    }

    const response = await request(app).post('/signup').send(signUpInfo)

    expect(response.status).toBe(201)

    const user = await User.findOne({
      email: signUpInfo.email
    })

    expect(user).toBeDefined()
    expect(user?.id).not.toBeNull()
    expect({
      id: user?.id,
      name: user?.name,
      email: user?.email
    }).toMatchObject(response.body)
    expect(user?.checkPassword(signUpInfo.password)).resolves.toBeTruthy()
  })
})
