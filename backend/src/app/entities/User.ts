import {
  Entity,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  DeleteDateColumn,
  OneToMany,
  BaseEntity,
  PrimaryColumn
} from 'typeorm'

import { Recipe, Token, RecipeAction } from '.'
import { UserConfiguration } from '../@types/User'

@Entity()
export default class User extends BaseEntity {
  @PrimaryColumn()
  id: string

  @CreateDateColumn()
  createdAt: Date

  @UpdateDateColumn()
  updatedAt: Date

  @DeleteDateColumn()
  deletedAt: Date

  @Column({ length: 50 })
  name: string

  @Column({ length: 100 })
  email: string

  @Column({ length: 100, nullable: true })
  facebookId: string

  @Column({ length: 100, nullable: true })
  googleId: string

  @Column({ length: 255, nullable: true })
  avatarUrl: string

  @Column({ type: 'jsonb', nullable: true })
  configuration: UserConfiguration

  @OneToMany(() => Token, token => token.user)
  tokens: Token[]

  @OneToMany(() => Recipe, recipe => recipe.user)
  recipes: Recipe[]

  @OneToMany(() => RecipeAction, recipeAction => recipeAction.user)
  actions: RecipeAction[]
}
