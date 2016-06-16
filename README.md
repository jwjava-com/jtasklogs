# **Tasklogs** -- *a time tracking tool*

This is intended as a Java replacement for the older Perl Tasklogs project.

Resources:
- [Tasklogs -- Perl version (on GitHub)](https://github.com/jonwarren/tasklogs)

Intended Functionality:
- start task
- stop task
  + implied by [start task] if new task
  + implied by [stop day]
- start day
  + implied by [start task] if 1st task of day
- stop day
  + implied by [start task] if task name is "quit"
- current task time
- current day summary (work vs break breakdown)
- start week
  + implied by [start day] if 1st day of week
- stop week
- end of week report
- rename task
- adjust task start time
- adjust task stop time
  + implied by [adjust task start time] of following task
