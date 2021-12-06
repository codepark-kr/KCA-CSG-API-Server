import React, { useCallback, useEffect, Component } from 'react';
import GSTC from 'gantt-schedule-timeline-calendar';
import { Plugin as TimelinePointer } from 'gantt-schedule-timeline-calendar/dist/plugins/timeline-pointer.esm.min.js';
import { Plugin as Selection } from 'gantt-schedule-timeline-calendar/dist/plugins/selection.esm.min.js';
import { Plugin as ItemResizing } from 'gantt-schedule-timeline-calendar/dist/plugins/item-resizing.esm.min.js';
import { Plugin as ItemMovement } from 'gantt-schedule-timeline-calendar/dist/plugins/item-movement.esm.min.js';
import '../assets/scss/gstc.scss';
// import 'gantt-schedule-timeline-calendar/dist/style.css';

let gstc, state;

function generateRows() {
  const rows = {};
  for (let i = 1; i <11; i++) {
    const id = GSTC.api.GSTCID(i.toString());
    rows[id] = {
      id,
      label: `CAT. ${i}`,
    };
  }
  return rows;
}

function generateItems() {
  const items = {};
  // @ts-ignore
  let start = GSTC.api.date().startOf('day').subtract(6, 'day');
  for (let i = 1; i < 101; i++) {
    const id = GSTC.api.GSTCID(i.toString());
    const rowId = GSTC.api.GSTCID(Math.floor(Math.random() * 100).toString());
    start = start.add(1, 'day');
    items[id] = {
      id,
      label: `Item ${i}`,
      rowId,
      time: {
        start: start.valueOf(),
        end: start.add(1, 'day').endOf('day').valueOf(),
      },
    };
  }
  return items;
}

function initializeGSTC(element) {
  const config = {
    licenseKey:
      '====BEGIN LICENSE KEY====\nXOfH/lnVASM6et4Co473t9jPIvhmQ/l0X3Ewog30VudX6GVkOB0n3oDx42NtADJ8HjYrhfXKSNu5EMRb5KzCLvMt/pu7xugjbvpyI1glE7Ha6E5VZwRpb4AC8T1KBF67FKAgaI7YFeOtPFROSCKrW5la38jbE5fo+q2N6wAfEti8la2ie6/7U2V+SdJPqkm/mLY/JBHdvDHoUduwe4zgqBUYLTNUgX6aKdlhpZPuHfj2SMeB/tcTJfH48rN1mgGkNkAT9ovROwI7ReLrdlHrHmJ1UwZZnAfxAC3ftIjgTEHsd/f+JrjW6t+kL6Ef1tT1eQ2DPFLJlhluTD91AsZMUg==||U2FsdGVkX1/SWWqU9YmxtM0T6Nm5mClKwqTaoF9wgZd9rNw2xs4hnY8Ilv8DZtFyNt92xym3eB6WA605N5llLm0D68EQtU9ci1rTEDopZ1ODzcqtTVSoFEloNPFSfW6LTIC9+2LSVBeeHXoLEQiLYHWihHu10Xll3KsH9iBObDACDm1PT7IV4uWvNpNeuKJc\npY3C5SG+3sHRX1aeMnHlKLhaIsOdw2IexjvMqocVpfRpX4wnsabNA0VJ3k95zUPS3vTtSegeDhwbl6j+/FZcGk9i+gAy6LuetlKuARjPYn2LH5Be3Ah+ggSBPlxf3JW9rtWNdUoFByHTcFlhzlU9HnpnBUrgcVMhCQ7SAjN9h2NMGmCr10Rn4OE0WtelNqYVig7KmENaPvFT+k2I0cYZ4KWwxxsQNKbjEAxJxrzK4HkaczCvyQbzj4Ppxx/0q+Cns44OeyWcwYD/vSaJm4Kptwpr+L4y5BoSO/WeqhSUQQ85nvOhtE0pSH/ZXYo3pqjPdQRfNm6NFeBl2lwTmZUEuw==\n====END LICENSE KEY====',
    plugins: [TimelinePointer(), Selection(), ItemResizing(), ItemMovement()],
    list: {
      columns: {
        data: {
          [GSTC.api.GSTCID('id')]: {
            id: GSTC.api.GSTCID('id'),
            width: 40,
            data: ({ row }) => GSTC.api.sourceID(row.id),
            header: {
              content: 'NO.',
            },
          },
          [GSTC.api.GSTCID('label')]: {
            id: GSTC.api.GSTCID('label'),
            width: 80,
            data: 'label',
            header: {
              content: 'CAT.',
            },
          },
        },
      },
      rows: generateRows(),
    },
    chart: {
      items: generateItems(),
    },
  };

  state = GSTC.api.stateFromConfig(config);

  gstc = GSTC({
    element,
    state,
  });
}

function DailySchedules() {
  const callback = useCallback((element) => {
    if (element) initializeGSTC(element);
  }, []);

  useEffect(() => {
    return () => {
      if (gstc) {
        gstc.destroy();
      }
    };
  });

  function updateFirstRow() {
    state.update(`config.list.rows.${GSTC.api.GSTCID('0')}`, (row) => {
      row.label = 'Changed dynamically';
      return row;
    });
  }

  function updateDaily() {
    state.update('config.chart.time.zoom', 20);
  }
  function updateHourly() {
    state.update('config.chart.time.zoom', 13);
  }

  return (
    <div className="outermost-dailyScheduler">
        <div className="inner-dailyScheduler">
            <div className="buttons-wrapper">
                {/* <button onClick={ updateFirstRow }>Update first row</button> */}
                <button onClick={ updateDaily } className="dailyScheduler-btns">-</button>
                <button onClick={ updateHourly } className="dailyScheduler-btns">+</button>
            </div>
            <div className="gstc-wrapper" ref={ callback }></div>
        </div>
    </div>
  );
}

export default DailySchedules;
