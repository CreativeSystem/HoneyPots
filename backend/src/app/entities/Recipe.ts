import {
  Entity,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  DeleteDateColumn,
  BaseEntity,
  PrimaryColumn,
  ManyToOne,
  OneToMany
} from 'typeorm'

import { RecipeContent } from '../@types/Recipe'
import RecipeAction from './RecipeAction'
import User from './User'

@Entity()
export default class Recipe extends BaseEntity {
  @PrimaryColumn()
  id: string

  @CreateDateColumn()
  createdAt: Date

  @UpdateDateColumn()
  updatedAt: Date

  @DeleteDateColumn()
  deletedAt: Date

  @Column({ type: 'jsonb', nullable: true })
  content?: RecipeContent

  @ManyToOne(() => User, user => user.recipes, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
    nullable: false
  })
  user: User

  @OneToMany(() => RecipeAction, recipeAction => recipeAction.recipe)
  actions: RecipeAction[]
}
