import {
  Entity,
  Column,
  CreateDateColumn,
  ManyToOne,
  BaseEntity
} from 'typeorm'

import { Recipe, User } from '.'

export enum RecipeActionType {
  LIKED = 'LIKED',
  VISUALIZED = 'VISUALIZED'
}

@Entity()
export default class RecipeAction extends BaseEntity {
  @Column({ update: false, enum: RecipeActionType, length: 30, primary: true })
  action: RecipeActionType

  @CreateDateColumn()
  createdAt: Date

  @ManyToOne(() => User, user => user.actions, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
    nullable: false,
    primary: true
  })
  user: User

  @ManyToOne(() => Recipe, recipe => recipe.actions, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
    nullable: false,
    primary: true
  })
  recipe: Recipe
}
