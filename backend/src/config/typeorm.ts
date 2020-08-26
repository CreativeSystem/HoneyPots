import './env'
import { ConnectionOptions } from 'typeorm'
import { SnakeNamingStrategy } from 'typeorm-naming-strategies'

export = {
  type: process.env.DATABASE_TYPE || 'postgres',
  url: process.env.DATABASE_URL,
  database: process.env.DATABASE_NAME,
  synchronize: false,
  logging: process.env.DATABASE_LOGGER === 'true',
  namingStrategy: new SnakeNamingStrategy(),
  entities: ['src/app/entities/**/*.ts'],
  migrations: ['src/database/migration/**/*.ts'],
  subscribers: ['src/app/subscriber/**/*.ts'],
  cli: {
    entitiesDir: 'src/app/entities',
    migrationsDir: 'src/database/migration',
    subscribersDir: 'src/app/subscriber'
  }
} as ConnectionOptions
