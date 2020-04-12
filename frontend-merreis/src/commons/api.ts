import { Expense } from './../types/models';

let data: Expense[] = [];

export const save = (expense: Expense): Expense[] => {
  return data = [...data, expense];
};

export const list = (): Expense[] => {
  return data;
};

export default { save, list };