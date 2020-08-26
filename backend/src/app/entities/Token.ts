import {
  Entity,
  Column,
  PrimaryColumn,
  CreateDateColumn,
  ManyToOne,
  BaseEntity
} from 'typeorm'

import { User } from '.'

export enum TokenType {
  PasswordReset = 'PASSWORD_RESET',
  EmailVerification = 'EMAIL_VERIFICATION',
  LoggedUser = 'LOGGED_USER'
}

@Entity()
export default class Token extends BaseEntity {
  @PrimaryColumn({ update: false })
  token: string

  @Column({ update: false, enum: TokenType })
  type: TokenType

  @CreateDateColumn()
  createdAt: Date

  @ManyToOne(() => User, user => user.tokens, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
    nullable: false
  })
  user: User
}
