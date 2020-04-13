export enum Recurrency {
  UNIQUE = "Unique",
  ANNUAL = "Annual", 
  MONTHLY = "Monthly"
}

export interface Group {
  id?: number
  name: string
}
export interface Expense {
  value: number | null
  group?: Group | null
  description?: string
  recurrency?: Recurrency | null
}
