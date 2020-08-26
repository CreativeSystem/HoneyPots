import bcrypt from 'bcrypt'
import {
  Entity,
  Column,
  PrimaryGeneratedColumn,
  CreateDateColumn,
  UpdateDateColumn,
  DeleteDateColumn,
  OneToMany,
  BeforeUpdate,
  BeforeInsert,
  BaseEntity
} from 'typeorm'

import Token from './Token'

@Entity()
export default class User extends BaseEntity {
  @PrimaryGeneratedColumn('uuid')
  id: string

  @CreateDateColumn()
  createdAt: Date

  @UpdateDateColumn()
  updatedAt: Date

  @DeleteDateColumn()
  deletedAt: Date

  @Column({ length: 80 })
  name: string

  @Column({ length: 80 })
  email: string

  @OneToMany(() => Token, token => token.user)
  tokens: Token[]

  @Column({ length: 80 })
  private passwordHash: string

  private passwordNeedHash = false
  public set password(password: string) {
    this.passwordNeedHash = true
    this.passwordHash = password
  }

  public get password() {
    return this.passwordHash
  }

  public async checkPassword(password: string) {
    return await bcrypt.compare(password, this.passwordHash)
  }

  private async hashPassword() {
    if (this.passwordNeedHash) {
      this.passwordHash = await bcrypt.hash(this.password, 8)
    }
  }

  @BeforeInsert()
  async beforeInsert() {
    await this.hashPassword()
  }

  @BeforeUpdate()
  async beforeUpdate() {
    await this.hashPassword()
  }
}
