import {
  MigrationInterface,
  QueryRunner,
  Table,
  TableForeignKey
} from 'typeorm'

export class RecipeMigration1605212762666 implements MigrationInterface {
  name = 'RecipeMigration1605212762666'

  tableName = 'recipe'

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
            name: 'content',
            type: 'jsonb',
            isNullable: true
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
