import { MigrationInterface, QueryRunner, Table } from 'typeorm'

export class UserMigration1605196754200 implements MigrationInterface {
  name = 'UserMigration1605196754200'
  tableName = 'user'

  public async up(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.createTable(
      new Table({
        name: this.tableName,
        columns: [
          {
            name: 'id',
            type: 'varchar',
            isPrimary: true
          },
          {
            name: 'created_at',
            type: 'timestamp',
            default: 'now()'
          },
          {
            name: 'updated_at',
            type: 'timestamp',
            default: 'now()'
          },
          {
            name: 'deleted_at',
            type: 'timestamp',
            isNullable: true
          },
          {
            name: 'name',
            type: 'varchar',
            length: '50'
          },
          {
            name: 'email',
            type: 'varchar',
            length: '100'
          },
          {
            name: 'facebook_id',
            type: 'varchar',
            length: '100',
            isNullable: true
          },
          {
            name: 'google_id',
            type: 'varchar',
            length: '100',
            isNullable: true
          },
          {
            name: 'avatar_url',
            type: 'varchar',
            length: '255',
            isNullable: true
          },
          {
            name: 'configuration',
            type: 'jsonb',
            isNullable: true
          }
        ]
      })
    )
  }

  public async down(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.dropTable(this.tableName)
  }
}
