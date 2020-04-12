import React, { useState } from "react";
import { handleInputChange, handleSelectChange } from "../../commons/utils";
import UInput from "../../components/forms/UInput";
import USelect from "../../components/forms/USelect";
import { Expense, Group } from "../../types/models";

export interface Props {}

const FormExpenses: React.FunctionComponent<Props> = () => {
  
  const defaultExpense: Expense = {
    tags: [],
    group: null,
    description: "",
  };

  const [expense, setExpense] = useState<Expense>(defaultExpense);

  return (
    <>
      <UInput
        name="description"
        label="Description"
        value={expense.description}
        onChange={handleInputChange(setExpense)}
      />
      <USelect
        name="group"
        label="Group"
        value={expense.group}
        options={groups}
        getOptionLabel={(option: Group) => option.name}
        onChange={handleSelectChange(setExpense, "group")}
      />
      <button onClick={() => console.log(expense)}>Enviar</button>
    </>
  );
};

const groups: Group[] = [{ id: 1, name: "Lazer" }];

export default FormExpenses;
