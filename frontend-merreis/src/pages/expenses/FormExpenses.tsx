import React, { useState } from "react";
import { handleInputChange, handleSelectChange, toOption } from "../../commons/utils";
import UInput from "../../components/forms/UInput";
import USelect, { Option } from "../../components/forms/USelect";
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
        onChange={handleSelectChange(setExpense, "group")}
        options={groupsOptions}
        name="Group"
      />
      <button onClick={() => console.log(expense)}>Enviar</button>
    </>
  );
};

const groups: Group[] = [{ id: 1, name: "Lazer" }];
const groupsOptions: Option<Group>[] = groups.map((group: Group) =>
  toOption<Group>(group, group.name)
);

export default FormExpenses;
