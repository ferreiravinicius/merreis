import React, { useState } from "react";
import USelect, { Option } from "../../components/forms/USelect";
import { Expense, Group } from "../../types/models";
import { toOption } from "../../commons/utils";

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
      <USelect
        onChange={(_: any, value: any) => setExpense({ ...expense, group: value })}
        options={groupsOptions}
        name="Group"
      />
      <button onClick={() => console.log(expense)}>Enviar</button>
    </>
  );
};

const groups: Group[] = [{ id: 1, name: "Lazer" }];
const groupsOptions: Option<Group>[] = groups.map((group: Group) => toOption<Group>(group, group.name));

export default FormExpenses;
