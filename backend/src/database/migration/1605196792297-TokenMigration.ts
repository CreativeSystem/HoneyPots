import {
  MigrationInterface,
  QueryRunner,
  Table,
  TableForeignKey
} from 'typeorm'

export class TokenMigration1605196792297 implements MigrationInterface {
  name = 'TokenMigration1605196792297'

  tableName = 'token'

  foreignKeys = [
    new TableForeignKey({
      columnNames: ['user_id'],
      referencedColumnNames: ['id'],
      referencedTableName: 'user',
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    })
  ]

  public async up(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.createTable(
      new Table({
        name: this.tableName,
        columns: [
          {
            name: 'key',
            type: 'varchar',
            length: '100',
            isPrimary: true
          },
          {
            name: 'type',
            type: 'varchar',
            length: '30'
          },
          {
            name: 'created_at',
            type: 'timestamp',
            default: 'now()'
          },
          {
            name: 'expires_at',
            type: 'timestamp'
          },
          {
            name: 'user_id',
            type: 'varchar'
          }
        ]
      })
    )

    await queryRunner.createForeignKeys(this.tableName, this.foreignKeys)
  }

  public async down(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.dropTable(this.tableName)
  }
}
