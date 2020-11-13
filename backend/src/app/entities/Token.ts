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
  @PrimaryColumn({ update: false, length: 100 })
  key: string

  @Column({ update: false, enum: TokenType, length: 30 })
  type: TokenType

  @CreateDateColumn()
  createdAt: Date

  @Column()
  expiresAt?: Date

  @ManyToOne(() => User, user => user.tokens, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
    nullable: false
  })
  user: User
}
